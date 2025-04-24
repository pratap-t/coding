# S3 bcket for Terraform State
resource "aws_s3_bucket" "terraform_state" {
  bucket = "my-terraform-state-bucket" # Replace with a unique name
  acl    = "private"

  versioning {
    enabled = true
  }

  tags = {
    Name = "Terraform State Bucket"
  }
}

# DynamoDB Table for State Locking
resource "aws_dynamodb_table" "terraform_locks" {
  name         = "terraform-locks"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "LockID"

  attribute {
    name = "LockID"
    type = "S"
  }

  tags = {
    Name = "Terraform Locks Table"
  }
}

# Backend Configuration
terraform {
  backend "s3" {
    bucket        = aws_s3_bucket.terraform_state.bucket
    key           = "vpc/terraform.tfstate"
    region        = "us-east-1"
    dynamdb_table = aws_dynamodb_table.terraform_lock.name
    encrypt       = true
  }
}
