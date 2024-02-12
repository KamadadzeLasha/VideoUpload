import os
import requests
from concurrent.futures import ThreadPoolExecutor

def send_request(url, files):
    response = requests.post(url, files=files)
    return response.text

# Define the URL
url = 'http://localhost:8081'

# Define the headers
headers = {}

# Define the directory containing the videos
video_dir = '/home/don/Videos'

# Get a list of all files in the directory
video_files = [os.path.join(video_dir, f) for f in os.listdir(video_dir) if os.path.isfile(os.path.join(video_dir, f))]

# Number of requests to send

# Create a thread pool
with ThreadPoolExecutor(max_workers = 100) as executor:
    # Submit requests
    futures = []
    for video_file in video_files:
        files = {
            'multipartFile': (os.path.basename(video_file), open(video_file, 'rb')),
            'targetPlatform': (None, 'FACEBOOK')
        }
        futures.append(executor.submit(send_request, url, files))

    # Wait for all requests to complete
    for future in futures:
        response = future.result()
        print(response)
