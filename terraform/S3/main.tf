# TODO: configure in the .env

resource "aws_s3_bucket" "spring_boot_jays" {
    bucket = "springbootjays"

    tags = {
        Name        = "Jays"
        Environment = "Dev"
    }
}

resource "aws_s3_bucket_cors_configuration" "example" {
    bucket = aws_s3_bucket.spring_boot_jays.id

    cors_rule {
        allowed_headers = ["*"]
        allowed_methods = ["GET", "POST"]
        # TODO: should be specific domain "localhost" and port
        # TODO: should be configurable
        allowed_origins = [var.allowed_origin]
        expose_headers  = ["ETag"]
        max_age_seconds = 3000
    }
}

resource "aws_s3_bucket_policy" "bucket_policy_spring_boot_jays" {
    bucket = aws_s3_bucket.spring_boot_jays.id

    policy = <<EOF
    {
        "Version": "2012-10-17",
        "Id": "RestrictAccessToSpringBootJaysBucket",
        "Statement": [
            {
                "Sid": "AllowS3UserSpringBootJays",
                "Effect": "Allow",
                "Principal": {
                    "AWS": [
                        "arn:aws:iam::${var.account_number}:user/s3_user_spring_boot_jays",
                        "arn:aws:iam::${var.account_number}:root"
                    ]
                },
                "Action": [
                    "s3:GetObject",
                    "s3:PutObject",
                    "s3:ListBucket",
                    "s3:DeleteBucket",
                    "s3:DeleteObject"
                ],
                "Resource": [
                    "arn:aws:s3:::springbootjays",
                    "arn:aws:s3:::springbootjays/*"
                ]
            },
            {
                "Sid": "DenyAccessToOthers",
                "Effect": "Deny",
                "NotPrincipal": {
                    "AWS": [
                        "arn:aws:iam::${var.account_number}:user/s3_user_spring_boot_jays",
                        "arn:aws:iam::${var.account_number}:root"
                    ]
                },
                "Action": [
                    "s3:GetObject",
                    "s3:PutObject",
                    "s3:ListBucket"
                ],
                "Resource": [
                    "arn:aws:s3:::springbootjays",
                    "arn:aws:s3:::springbootjays/*"
                ]
            }
        ]
    }
    EOF
}

output "bucket_name" {
    value = aws_s3_bucket.spring_boot_jays.bucket
}
