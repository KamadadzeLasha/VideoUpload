import requests
import random

def download_random_video(api_key, video_filename):
    # Construct the API request URL
    url = f"https://pixabay.com/api/videos/?key={api_key}&per_page=100"

    # Send the API request
    response = requests.get(url)
    data = response.json()

    # Check if the request was successful
    if response.status_code != 200:
        print(f"Error: Failed to fetch data from Pixabay API. Status code: {response.status_code}")
        return

    # Extract a random video from the response
    videos = data.get('hits')
    if not videos:
        print("Error: No videos found in the response")
        return

    random_video = random.choice(videos)
    video_url = random_video['videos']['large']['url']

    # Download the video
    with requests.get(video_url, stream=True) as response:
        with open(video_filename, 'wb') as video_file:
            for chunk in response.iter_content(chunk_size=8192):
                video_file.write(chunk)

    print(f"Downloaded sample video: {video_filename}")

# Replace 'YOUR_API_KEY' with your Pixabay API key
api_key = '42310077-8045ad5c27990365fbed30e3b'

# Replace 'DESTINATION_FOLDER' with the folder where you want to save the video

for i in range(100):
    download_random_video(api_key, f"video{i + 1}.mp4")
