# Getting Started
We have three deployment options available for the backend server:
1. Local Deployment (for testing purposes):
   - Utilizes AWS S3 for image storage.
   - Utilizes a local MongoDB instance for data storage.
2. AWS Deployment:
   - Utilizes AWS S3 for image storage.
   - Utilizes AWS EKS (Elastic Kubernetes Service) to deploy the backend server.
   - Utilizes MongoDB Atlas, a managed MongoDB service by MongoDB, to support data storage.
3. Deployment to an Ubuntu server (self-hosted):
   - ???

## Local
AWS S3 + mongodb (local)

### Prepare application.properties
1. Copy and rename `application.properties.example` to `application.properties`.
2. Replace fields with your own values.

### Use Terraform to create IAM and S3 Bucket
To create an IAM user, an S3 bucket, and attach policies using Terraform:
1. Sign up for an AWS account. After signing up, you will have a root user assigned to you automatically. However, it is not recommended to use the root user for your project, and AWS does not generate an access key for it by default.
2. Create a second IAM user named `TerraformInJays` with full permissions for IAM and S3. Remember, we require two IAM users: one exclusively for the S3 bucket and the second user, `TerraformInJays`, for executing the Terraform script. You don't need to create the policy or assign permissions immediately during IAM creation; we will use an inline policy later.
   1. Please attach the policy that allows creating IAM users to `TerraformInJays`.
   ```json
   {
     "Version": "2012-10-17",
     "Statement": [
       {
         "Sid": "VisualEditor0",
         "Effect": "Allow",
         "Action": "iam:*",
         "Resource": "*"
       }
     ]
   }
   ```
   2. Please attach the policy that allows creating S3 to `TerraformInJays`.
   ```json
   {
     "Version": "2012-10-17",
     "Statement": [
       {
         "Sid": "VisualEditor0",
         "Effect": "Allow",
         "Action": "s3:*",
         "Resource": "*"
       }
     ]
   }
   ```
   3. Generate the Access Key and Secret Key, as these values will be required for step 3.
3. Create two credentials files:
   1. Copy and rename `terraform/credentials.tf.example` to `terraform/credentials.tf`. Ensure to replace sensitive values in the newly renamed file.
   2. Copy and rename `S3/credentials.tf.exampel` to `S3/credentials.tf`. Ensure to replace sensitive values in the newly renamed file.
4. // TODO: Also remember to change all 'jays' to your project's nickname.
   1. including the one in the `AuthService` and `application.properties`.
5. Begin to run terraform scripts:
   1. Install terraform from https://developer.hashicorp.com/terraform/downloads
   2. Navigate to the terraform folder and run `terraform init`, then
   3. run `terraform plan`, finally
   4. run `terraform apply`.
6. After applying to terraform scripts, these four fields will appear in `application.properties` for using in `src` code.
   1. `aws.S3.properties.accessKey`
   2. `aws.S3.properties.secretKey`
   3. `aws.S3.properties.region`
   4. `aws.S3.properties.bucketName`
7. Summary:
   1. There are three user roles:
      * Root User: Manually create this user when opening an account.
      * IAM User `TerraformInJays`: Manually create this user for the Terraform script.
      * `s3_user_spring_boot_jays`: This user is exclusively for the S3 project.
   2. Use `TerraformInJays` user for Terraform scripts. The S3 bucket `springbootjays` and IAM user `s3_user_spring_boot_jays` will be automatically generated under this user.
   3. `s3_user_spring_boot_jays`: For this project only, credentials will be generated and placed at the end of the `application.properties` file.

### Install mongodb
Don't forget to install mongodb on-premise if you want to demo it locally. The database name should be `Spring_Boot_Jays`, which is defined in `application.properties`.

# Swagger
Swagger is installed so after running this project, go to http://localhost:8080/swagger-ui/ to check all APIs.


