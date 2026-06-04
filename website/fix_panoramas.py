from PIL import Image
import os
import glob

panoramas_dir = os.path.join(os.getcwd(), 'public', 'images', 'panoramas')
files = glob.glob(os.path.join(panoramas_dir, '*.png'))

for file_path in files:
    print(f"Processing {file_path}")
    img = Image.open(file_path)
    w, h = img.size
    if w == h * 2:
        print("Already 2:1")
        continue
    
    # Tile 2x horizontally
    new_img = Image.new('RGB', (w * 2, h))
    new_img.paste(img, (0, 0))
    new_img.paste(img, (w, 0))
    
    new_img.save(file_path)
    print(f"Successfully fixed {file_path}")
