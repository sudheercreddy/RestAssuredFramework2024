pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
                
        stage('Run Regression API Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/sudheercreddy/RestAssuredFramework2024.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_regression_GoRestNMockingCICD.xml"
                    
                }
            }
        }
                
     
        stage('Publish QA Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        
        
         stage("Deploy to STAGE"){
            steps{
                echo("deploy to STAGE done")
            }
        }
        
        stage('Run Sanity API Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/sudheercreddy/RestAssuredFramework2024.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/testrunners/testng_sanityCICD.xml"
                    
                }
            }
        }
        
        stage("Stage Report Allure"){
            steps{
                echo("deploy to qa done")
            }
        }
         
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
}
