"""
1. requests is THE library for HTTP in Python
2. Simple, intuitive API good for quick API testing

pip install requests
"""

import requests
import json

# base URL
BASE_URL = "https://jsonplaceholder.typicode.com"

def section_header(title):
    print(f"\n{'='*60}")
    print(title)
    print('='*60)

def demo_basic_get():
    # simple get request
    section_header("Basic GET request")
    response = requests.get(f"{BASE_URL}/posts/1")

    print(f"Status code: {response.status_code}")
    print(f"Content-Type: {response.headers['Content-Type']}")
    print(f"Response time: {response.elapsed.total_seconds():.3f} seconds")

    # parse JSON response
    data = response.json()
    print(f"\nPost title: {data['title'][:50]}")
    print(f"User ID: {data['userId']}")

def demo_get_with_params():
    """
    Query parameters can be passed with dict.
    Much cleaner than string concatenation.
    """
    section_header("GET with query parameters")
    params = {
        "userID": 1,
        "_limit": 5
    }
    response = requests.get(f"{BASE_URL}/posts", params=params)

    print(f"Request URL: {response.url}")
    print(f"Status code: {response.status_code}")

    posts = response.json()
    print(f"Number of posts returned: {len(posts)}")
    
    for post in posts:
        print(f"\t-Post {post['id']}: {post['title'][:30]}...")

if __name__ == "__main__":
    demo_basic_get()
    demo_get_with_params()

