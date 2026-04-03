class ResponseBuilder:

    @staticmethod
    def build_login_response(has_token):
        if has_token:
            print("Server - Received login, sending request to client...")
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
            print("Server - Received get role, sending request to client...")
            print(has_role)
            print(type(has_role))
            response = {
                "role": has_role
            }
            print(response)
        else:
            print("Server - Get role Failed")
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_add_user_response(user):
        if user is not None:
            print("Server - Received add user, sending request to client...")
            response = {
                "status": "success",
            }
        else:
            print("Server - Add user Failed")
            response = {
                "status": "error",
            }
        return response

    @staticmethod
    def build_user_exists_response():
        print("Server - User exists Failed")
        response = {
            "status": "error",
        }
        return response

