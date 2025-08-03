
def scmVars
node('master')
{
        stage('Cleanup')
        {
        deleteDir()
        }
        stage('Clone Sources')
        {
        scmVars=checkout scm
        currentBuild.description=scmVars.GIT_LOCAL_BRANCH
        }
        stage('Build and Run')
        {
        sh "${mvnHome}/bin/mvn clean test"
        }
            stage('Archive Reports') {
                junit '**/target/surefire-reports/*.xml'
            }
    }