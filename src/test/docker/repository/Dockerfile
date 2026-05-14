FROM ${docker.tests.repository.base}

${docker.tests.repository.preRun}

COPY *.amp ${docker.tests.repository.ampPath}/
COPY activemq-broker*.jar ${docker.tests.repository.webappPath}/WEB-INF/lib/

COPY alfresco.xml ${docker.tests.repository.tomcatPath}/conf/Catalina/localhost/
COPY alfresco-global.addition.properties ${docker.tests.repository.tomcatPath}/shared/classes/alfresco/
COPY dev-log4j.properties dev-log4j2.properties ${docker.tests.repository.tomcatPath}/shared/classes/alfresco/extension/

# merge additions to alfresco-global.properties
RUN echo "" >> ${docker.tests.repository.tomcatPath}/shared/classes/alfresco-global.properties \
    && cat ${docker.tests.repository.tomcatPath}/shared/classes/alfresco/alfresco-global.addition.properties >> ${docker.tests.repository.tomcatPath}/shared/classes/alfresco-global.properties \
    && sed -i 's/<secure>true<\/secure>/<secure>false<\/secure>/' ${docker.tests.repository.tomcatPath}/conf/web.xml

RUN chown -R ${docker.tests.repository.chown.mask} \
             ${docker.tests.repository.webappPath} \
             ${docker.tests.repository.tomcatPath}/shared/classes/*

${docker.tests.repository.postRun}