## RESOURCE OWNER PASSWORD CREDENTIALS GRANT

    curl -s acme:acmesecret@localhost:9999/uaa/oauth/token  \
     -d grant_type=password \
     -d client_id=acme \
     -d scope=webshop \
     -d username=user \
     -d password=password
