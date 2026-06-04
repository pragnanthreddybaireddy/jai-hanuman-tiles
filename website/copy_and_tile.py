from PIL import Image
import os
import glob
import re

brain_dir = r"C:\Users\pragn\.gemini\antigravity-ide\brain\9bb45ea8-228b-402c-ac80-736cb0cd6a37"
panoramas_dir = os.path.join(os.getcwd(), 'public', 'images', 'panoramas')

files = glob.glob(os.path.join(brain_dir, '*_360_*.png'))

for file_path in files:
    filename = os.path.basename(file_path)
    match = re.match(r'(.+?)_360_\d+\.png', filename)
    if not match: continue
    
    base_name = match.group(1)
    dest_path = os.path.join(panoramas_dir, f"{base_name}.png")
    
    print(f"Processing {base_name}...")
    img = Image.open(file_path)
    w, h = img.size
    
    if w == h * 2:
        img.save(dest_path)
    else:
        new_img = Image.new('RGB', (w * 2, h))
        new_img.paste(img, (0, 0))
        new_img.paste(img, (w, 0))
        new_img.save(dest_path)
        
    print(f"Saved {dest_path}")
