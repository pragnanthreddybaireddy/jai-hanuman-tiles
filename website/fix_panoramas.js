import Jimp from 'jimp';
import fs from 'fs';
import path from 'path';

const panoramasDir = path.join(process.cwd(), 'public', 'images', 'panoramas');

async function fixPanoramas() {
  const files = fs.readdirSync(panoramasDir).filter(f => f.endsWith('.png'));

  for (const file of files) {
    const filePath = path.join(panoramasDir, file);
    console.log(`Processing ${file}...`);
    try {
      const image = await Jimp.read(filePath);
      const width = image.bitmap.width;
      const height = image.bitmap.height;

      // Create a new image that is 2x wider (e.g. 1024x1024 -> 2048x1024)
      const targetWidth = width * 2;
      const targetHeight = height; // To keep 2:1 aspect if it was 1:1. Wait, if it was 1024x1024, tiling 2x makes it 2048x1024 which is exactly 2:1!
      
      const newImage = new Jimp(targetWidth, targetHeight);
      
      // Tile original image twice horizontally
      newImage.composite(image, 0, 0);
      newImage.composite(image, width, 0);
      
      // Save over the original file
      await newImage.writeAsync(filePath);
      console.log(`Successfully fixed ${file} (new size: ${targetWidth}x${targetHeight})`);
    } catch (err) {
      console.error(`Error processing ${file}:`, err);
    }
  }
}

fixPanoramas();
