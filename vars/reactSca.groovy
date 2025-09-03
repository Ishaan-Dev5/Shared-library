def call(Map config)
{
  withSonarQubeEnv('sonarqube'){
    def scannerHome = tool 'sonar-scanner'
    sh """
      ${scannerHome}/bin/sonar-scanner \
      -Dsonar.projectKey=${config.projectkey} \
      -Dsonar.projectName=${config.projectName} \
      -Dsonar.sources=src \
      -Dsonar.projectVersion=${BUILD_NUMBER}
    """

    
  }
}

