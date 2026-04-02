class ResponseBuilder:

    @staticmethod
    def build_login_response(has_token):
        if has_token:
            print("Server - Received login, sending request to server...")
            response = {
                "token": has_token
            }
        else:
            print("Server - Login Failed")
            response = {
                "status": "error",
            }

        return response

    @staticmethod
    def build_get_role_response(has_role):
        if has_role:
            print("Server - Received get role, sending request to server...")
            response = {
                "role": str(has_role)
            }
        else:
            print("Server - Get role Failed")
            response = {
                "status": "error",
            }
        return response

