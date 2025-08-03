def scmVars
node('master') {
    stage('Cleanup') {
        deleteDir()
    }

    stage('Clone Sources') {
        scmVars = checkout scm
        currentBuild.description = scmVars.GIT_LOCAL_BRANCH
    }

    stage('Build and Run') {
        sh "mvn clean install"
    }

    stage('Publish Report') {
        publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: '.',
            reportFiles: 'custom-report.html',
            reportName: 'Custom API Report'
        ])
    }
}
