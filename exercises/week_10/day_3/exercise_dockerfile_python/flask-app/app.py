from flask import Flask, jsonify
import os
import socket
from datetime import datetime
import subprocess

app = Flask(__name__)

@app.route('/')
def home():
    return jsonify({
        'message': 'Welcome from Dockerized Flask v2!',
        'hostname': socket.gethostname(),
        'timestamp': datetime.now().isoformat(),
        'version': os.environ.get('APP_VERSION', '1.0.0')
    })

@app.route('/health')
def health():
   return jsonify({
       'status': 'healthy',
       'service': 'flask-demo'
   })

@app.route('/env')
def environment():
    # Show safe environment info
    return jsonify({
        'python_version': os.popen('python --version').read().strip(), # popen soft deprecated
        'app_version': os.environ.get('APP_VERSION', 'not set'),
        'environment': os.environ.get('FLASK_ENV', 'production')
    })

if __name__ == "__main__":
    port = int(os.environ.get('PORT', 5001)) # changed from 5000 (problematic with mac airplay)
    app.run(host='0.0.0.0', port=port)

