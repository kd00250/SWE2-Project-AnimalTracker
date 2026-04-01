import zmq
import json

def main():
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("tcp://127.0.0.1:5555")
    print("Server listening on port 5555...")

    while True:
        raw = socket.recv()
        message = raw.decode("utf-8")

        if message == "exit":
            print("Server - Received exit, shutting down.")
            break

        request = json.loads(message)
        print(f"Server - Received: {request}")

        response = {
            "status": "ok",
            "message": f"Got your message: {request.get('message', '')}"
        }
        socket.send(json.dumps(response).encode("utf-8"))

    socket.close()
    context.term()


if __name__ == "__main__":
    main()