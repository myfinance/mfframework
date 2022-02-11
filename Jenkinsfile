pipeline {
 agent none

 environment{
   //Snapshot Version
   VERSION = "0.21.0-alpha.${BUILD_ID}"
   //Release Version
   //VERSION = "0.19.0"

   K8N_IP = "192.168.100.73"
   NEXUS_URL = "${K8N_IP}:31001"
   MVN_REPO = "http://${NEXUS_URL}/repository/maven-releases/"
 }
 
 stages{
   stage('preperation'){
    agent {
        docker {
            image 'maven:3.8.4-eclipse-temurin-17-alpine'
        }
    }      
     steps {
       cleanWs()
       git credentialsId: 'github', url: "https://github.com/myfinance/mfbackend.git"
     }
   }
   stage('build'){
    agent {
        docker {
            image 'maven:3.8.4-eclipse-temurin-17-alpine'
        }
    }      
     steps {
       sh '''mvn versions:set -DnewVersion=${VERSION} --settings ./settings.xml'''
       sh '''mvn clean deploy -DtargetRepository=${MVN_REPO} --settings ./settings.xml'''
     }
   }     

 }
}