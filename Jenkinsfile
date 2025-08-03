def scmVars
node('master') {
    stage('Cleanup') {
        deleteDir()
    }
parameters {
  string defaultValue: 'QA', name: 'Environment', trim: true
}

    stage('Clone Sources') {
        scmVars = checkout scm
        currentBuild.description = scmVars.GIT_LOCAL_BRANCH
        echo {Environment}
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
