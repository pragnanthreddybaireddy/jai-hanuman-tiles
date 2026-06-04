const { Jimp } = require('jimp');
const fs = require('fs');
const path = require('path');

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

      // Skip if already 2:1 aspect ratio
      if (width === height * 2) {
        console.log(`Skipping ${file}, already 2:1 ratio`);
        continue;
      }

      const targetWidth = width * 2;
      const targetHeight = height;
      
      const newImage = new Jimp(targetWidth, targetHeight);
      
      newImage.composite(image, 0, 0);
      newImage.composite(image, width, 0);
      
      await newImage.writeAsync(filePath);
      console.log(`Successfully fixed ${file} (new size: ${targetWidth}x${targetHeight})`);
    } catch (err) {
      console.error(`Error processing ${file}:`, err);
    }
  }
}

fixPanoramas();
