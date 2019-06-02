/*
 * Copyright 2019 Acosix GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.acosix.alfresco.rest.client.jackson;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.module.SimpleModule;

import de.acosix.alfresco.rest.client.jackson.Wrapped.WrapType;
import de.acosix.alfresco.rest.client.model.authentication.TicketEntity;
import de.acosix.alfresco.rest.client.model.common.PaginatedList;

/**
 * @author Axel Faust
 */
public class EntryWrappedTests
{

    @Wrapped(WrapType.LIST)
    public static class TicketPaginatedList extends PaginatedList<TicketEntity>
    {
        // NO-OP
    }

    private static ObjectMapper mapper;

    @BeforeClass
    public static void setupClass()
    {
        final SimpleModule module = new SimpleModule();
        module.setDeserializerModifier(new BeanDeserializerModifier()
        {

            /**
             * {@inheritDoc}
             */
            @Override
            public JsonDeserializer<?> modifyDeserializer(final DeserializationConfig config, final BeanDescription beanDesc,
                    final JsonDeserializer<?> deserializer)
            {
                final Class<?> beanClass = beanDesc.getBeanClass();
                JsonDeserializer<?> resultDeserializer = deserializer;
                final Wrapped wrapped = beanClass.getAnnotation(Wrapped.class);
                if (wrapped != null)
                {
                    resultDeserializer = new WrappedFacadeDeserializer<>(beanClass, wrapped.value(), deserializer);
                }
                return resultDeserializer;
            }
        });

        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void ticketJSONWithoutEntry() throws Exception
    {
        // this test just verifies the unaltered mapper + entity work properly
        final String ticketJSON = "{\"id\":\"1234\",\"userId\":\"dummyUser\"}";
        final ObjectMapper mapper = new ObjectMapper();
        final TicketEntity ticket = mapper.readValue(ticketJSON, TicketEntity.class);

        Assert.assertEquals("1234", ticket.getId());
        Assert.assertEquals("dummyUser", ticket.getUserId());
    }

    @Test
    public void ticketJSONWithEntry() throws Exception
    {
        final String ticketJSON = "{\"entry\":{\"id\":\"1234\",\"userId\":\"dummyUser\"}}";
        final TicketEntity ticket = mapper.readValue(ticketJSON, TicketEntity.class);

        Assert.assertEquals("1234", ticket.getId());
        Assert.assertEquals("dummyUser", ticket.getUserId());
    }

    @Test
    public void ticketJSONSimppleListWithEntry() throws Exception
    {
        final String ticketJSON = "[{\"entry\":{\"id\":\"1234\",\"userId\":\"dummyUser1\"}}, {\"entry\":{\"id\":\"9876\",\"userId\":\"dummyUser2\"}}]";
        final TicketEntity[] tickets = mapper.readValue(ticketJSON, TicketEntity[].class);

        Assert.assertEquals(2, tickets.length);
        final TicketEntity ticket1 = tickets[0];
        Assert.assertEquals("1234", ticket1.getId());
        Assert.assertEquals("dummyUser1", ticket1.getUserId());

        final TicketEntity ticket2 = tickets[1];
        Assert.assertEquals("9876", ticket2.getId());
        Assert.assertEquals("dummyUser2", ticket2.getUserId());
    }

    @Test
    public void ticketJSONPaginatedListWithEntry() throws Exception
    {
        final String ticketJSON = "{\"list\": {\"entries\": [{\"entry\":{\"id\":\"1234\",\"userId\":\"dummyUser1\"}}, {\"entry\":{\"id\":\"9876\",\"userId\":\"dummyUser2\"}}]}}";
        final TicketPaginatedList ticketList = mapper.readValue(ticketJSON, TicketPaginatedList.class);
        final List<TicketEntity> tickets = ticketList.getEntries();

        Assert.assertEquals(2, tickets.size());
        final TicketEntity ticket1 = tickets.get(0);
        Assert.assertEquals("1234", ticket1.getId());
        Assert.assertEquals("dummyUser1", ticket1.getUserId());

        final TicketEntity ticket2 = tickets.get(1);
        Assert.assertEquals("9876", ticket2.getId());
        Assert.assertEquals("dummyUser2", ticket2.getUserId());
    }

    @Test
    public void ticketJSONWithNonEntryField() throws Exception
    {
        this.exceptionRule.expect(JsonMappingException.class);
        final String ticketJSON = "{\"nonEntry\":{\"id\":\"1234\",\"userId\":\"dummyUser\"}}";
        mapper.readValue(ticketJSON, TicketEntity.class);
    }

    @Test
    public void ticketJSONWithEntryAndSuperflousField() throws Exception
    {
        this.exceptionRule.expect(JsonMappingException.class);
        final String ticketJSON = "{\"entry\":{\"id\":\"1234\",\"userId\":\"dummyUser\"},\"superflous\":\"value\"}";
        mapper.readValue(ticketJSON, TicketEntity.class);
    }

    @Test
    public void ticketJSONSimpleListWithEntryAndSuperflousField() throws Exception
    {
        this.exceptionRule.expect(JsonMappingException.class);
        final String ticketJSON = "[{\"entry\":{\"id\":\"1234\",\"userId\":\"dummyUser1\"},\"superflous\":\"unnecessaryValue\"}, {\"entry\":{\"id\":\"9876\",\"userId\":\"dummyUser2\"}}]";
        mapper.readValue(ticketJSON, TicketEntity[].class);
    }
}
