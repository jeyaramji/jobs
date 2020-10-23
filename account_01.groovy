#!/usr/bin/env groovy

pipelineJob('example-pipeline-account-01') {
  definition {
    cps {
      script('''
        pipeline {
        parameters {
          choice(choices: ['ACCOUNT-01''], description: 'Name of the Account?', account_name: 'account01')
        }
          agent any
            stages {
              stage ('Build') {
                steps {
                  sh "echo 'Build phase - ${params.account_name}'
                }
              }
              stage ('Unit tests') {
                steps {
                  echo 'Unit testing phase'
                }
              }
              stage ('Deploy') {
                steps {
                  echo 'Deploy phase'
                }
              }
            }
        }
      '''.stripIndent())
      sandbox()
    }
  }
}