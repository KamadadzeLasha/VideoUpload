import subprocess
import json

def get_video_resolution(video_file):
    ffprobe_cmd = [
        'ffprobe',
        '-v', 'error',
        '-select_streams', 'v:0',
        '-show_entries', 'stream=width,height',
        '-of', 'json',
        video_file
    ]
    result = subprocess.run(ffprobe_cmd, capture_output=True, text=True, check=True)
    data = json.loads(result.stdout)
    width = int(data['streams'][0]['width'])
    height = int(data['streams'][0]['height'])
    return width, height

def process_video(input_file):
    temp_file = "output.mp4"
    width, height = get_video_resolution(input_file)
    print(f"width = {width}, height = {height}" )
    if width == 3840 and height == 2160:
        command = [
            'ffmpeg',
            '-i', input_file,
            '-vf', 'scale=1920:-1',
            '-c:a', 'copy',
            temp_file
        ]
        subprocess.run(command, check=True)
    elif width == 2160 and height == 3840:
        command = [
            'ffmpeg',
            '-i', input_file,
            '-vf', 'scale=-1:1920',
            '-c:a', 'copy',
            temp_file
        ]
        subprocess.run(command, check=True)
    else:
        # Do nothing if resolution is neither 3840x2160 nor 2160x3840
        return
    # Rename output file to video.mp4
    subprocess.run(['mv', '-f', temp_file, input_file], check=True)


for i in range(100):
    process_video(f"video{i + 1}.mp4")
