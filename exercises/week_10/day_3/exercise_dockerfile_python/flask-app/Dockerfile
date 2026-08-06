# use python runtime as a base image
FROM python:3.11-slim

# establish a working directory inside container
WORKDIR /app

# copy requirements into working directory
COPY requirements.txt .

# install dependencies
RUN pip install --no-cache-dir -r requirements.txt

# copy application code
COPY app.py .

# set environment variables
ENV APP_VERSION=1.0.0
ENV FLASK_ENV=production

# document the port
EXPOSE 5001

CMD ["python", "app.py"]
