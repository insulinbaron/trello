pipeline {
    agent any
    tools {
        maven 'apache-maven-3.8.4'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }
    triggers {
        cron(env.BRANCH_NAME == 'main' ? 'H H/12 * * *' : "")
    }
    parameters {
        choice(name: "suite", choices: "testng.xml\ntestng-api.xml\ntestng-ui.xml", description: "Тесты для запуска")
        string(name: "branch", defaultValue: "main", description: "Имя ветки для запуска")
    }
    stages {
        stage('Build') {
            steps {
                 git(url: 'https://github.com/insulinbaron/trello.git', branch: '${branch}')
            }
        }
        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }
        stage('Run test') {
            steps{
                 sh 'mvn test -Dsurefire.suiteXmlFiles=${suite}'
            }
        }
        stage('Report') {
                    steps {
                        allure([
                                includeProperties: false,
                                jdk              : '',
                                properties       : [],
                                reportBuildPolicy: 'ALWAYS',
                                results          : [[path: 'target/allure-results']]
                        ])
                    }
        }
    }
}