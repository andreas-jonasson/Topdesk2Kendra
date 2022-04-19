# TopDesk2Kendra
Transfers knowledge articles (KnowledgeItem) from TopDesk to be indexed by Kendra.

## Mappings from TopDesk to Kendra metadata:
        hmmm... Depends on domain?  ->  _category (String) **
        translation.creationDate 	->  _created_at (ISO 8601 encoded string) ***
        "TopDesk"                   ->  _data_source_id (String) ***
        translation.content.content	->  _document_body (String) ****
        number || id                ->  _document_id (String) ****
        translation.content.title	->  _document_title (String) ***
        "HTML"                      ->  _file_type (String) ****
        translation.modificationDate->  _last_updated_at ***
        translation.language        ->  _language_code (String) ****


## Configuration
 * `./config.json`     Contains the general configuration for the installation. Contains a reference to the credentials file, that is not checked into git.
 * `./default-credentials.json`    Shows how to set up the credentials.
# Welcome to your CDK Java project!

This is a blank project for Java development with CDK.

The `cdk.json` file tells the CDK Toolkit how to execute your app.

It is a [Maven](https://maven.apache.org/) based project, so you can open this project with any Maven compatible Java IDE to build and run tests.

## Useful commands

 * `mvn package`     compile and run tests
 * `cdk ls`          list all stacks in the app
 * `cdk synth`       emits the synthesized CloudFormation template
 * `cdk deploy`      deploy this stack to your default AWS account/region
 * `cdk diff`        compare deployed stack with current state
 * `cdk docs`        open CDK documentation

Enjoy!
