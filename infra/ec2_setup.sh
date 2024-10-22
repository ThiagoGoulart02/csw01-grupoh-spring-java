#!/bin/bash

PEM_FILE="./my-ec2-key.pem"
INSTANCE_IP="$(terraform output -raw instance_public_ip)"

chmod 400 "$PEM_FILE"
ssh -i "$PEM_FILE" ubuntu@"$INSTANCE_IP"
