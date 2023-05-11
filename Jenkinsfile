pipeline {
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }
    agent {
        any
    }
    parameters {
        choice(name: "tests", choices: "API\nUI", description: "Тесты для запуска")
        string(name: "branch", defaultValue: "main", description: "Имя ветки для запуска")
    }

    stages {
        stage('Build') {
            steps {
                 git(url: 'https://github.com/insulinbaron/trello.git', branch: '${params.branch}')
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
    }
}