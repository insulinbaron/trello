pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }
    agent {
        any
    }
    parameters {
        sting(name: "branch", defaultValue: "master", description: "Имя ветки для запуска")
    }

    stages {
        stage('Build') {
            steps {
                 git(url: '', branch: ${params.branch}, credentialId: '')
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run test') {
            steps{
                sh 'mvn test'
            }
        }

        stage('Report'){
            steps{
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildingPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']
                ])
            }
        }

    }
}