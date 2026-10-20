pipeline {
    agent any

    options {
        timestamps()
    }

    triggers {
        cron('H 9 * * *')
    }

    environment {
        PYTHON = 'C:\\Users\\Ram\\AppData\\Local\\Programs\\Python\\Python312\\python.exe'
    }

    stages {
        stage('Checkout from GitHub') {
            steps {
                checkout scm
                echo 'Source code downloaded from GitHub.'
            }
        }

        stage('Install Python Dependencies') {
            steps {
                bat '"%PYTHON%" --version'
                bat '"%PYTHON%" -m pip install -r python-tests\\requirements.txt'
            }
        }

        stage('Run Selenium Tests') {
            steps {
                script {
                    bat 'if not exist "python-tests\\reports" mkdir "python-tests\\reports"'

                    def result = bat(
                        returnStatus: true,
                        script: '"%PYTHON%" -m pytest python-tests -v -s --junitxml=python-tests\\reports\\pytest-results.xml'
                    )

                    junit(
                        allowEmptyResults: true,
                        testResults: 'python-tests/reports/pytest-results.xml'
                    )

                    if (result != 0) {
                        error('One or more Python Selenium tests failed.')
                    }
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts(
                artifacts: 'python-tests/reports/pytest-results.xml',
                allowEmptyArchive: true
            )
        }

        failure {
            mail(
                to: 'aratrikasharma86@gmail.com',
                subject: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """The BYEIT automated testing pipeline has failed.

Job: ${env.JOB_NAME}
Build: #${env.BUILD_NUMBER}
Result: ${currentBuild.currentResult}

Check the Jenkins console output for details:
${env.BUILD_URL}console
"""
            )
        }

       success {
    mail(
        to: 'YOUR_GMAIL@gmail.com',
        subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: """Hello,

Your BYEIT automated testing pipeline has completed successfully!

Job: ${env.JOB_NAME}
Build Number: #${env.BUILD_NUMBER}
Status: SUCCESS

All Selenium tests passed.

View the build details:
${env.BUILD_URL}

Regards,
BYEIT Jenkins Pipeline
"""
    )
}
    }
}