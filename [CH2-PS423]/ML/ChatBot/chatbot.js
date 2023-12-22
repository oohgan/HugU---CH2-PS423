const tf = require('@tensorflow/tfjs-node');
const fs = require('fs');
const handler = tf.io.fileSystem('/tfjs_model/model.json');
const model = tf.loadLayersModel(handler);
const tokenizerJson = fs.readFileSync('/tokenizer_chatbot_dict.json', 'utf8');
const tokenizer = JSON.parse(tokenizerJson);
const tagsClassJson = fs.readFileSync('/label_decoder.json');
const tagsClass = JSON.parse(tagsClassJson);
const datasetJson = fs.readFileSync('/Dataset/Mental_Health_Conversational.json', 'utf8');
const dataset = JSON.parse(datasetJson);




const replace_words = [
    ['ibu', ['ibuku', 'mami', 'mama']],
    ['ayah', ['ayahku', 'papa', 'daddy']],
    ['saudara', ['adikku', 'adek', 'kakakku', 'koko', 'cece']],
    ['teman', ['temanku']],
    ['meninggal', ['mati']]
]

function preprocessingText(sentence) {
  const filteredWords = sentence.toLowerCase().replace(/[^\w\d\s]/g, '');
  const words = filteredWords.split(/\s+/);

  const cleanedWords = [];
  for (let word of words) {
    let replaced = false;
    for (let [replacement, target] of replace_words) {
      if (target.includes(word)) {
        cleanedWords.push(replacement);
        replaced = true;
      }
    }
    if (!replaced) {
      cleanedWords.push(word);
    }
  }

  return cleanedWords.join(' ');
}

function getKeyByValue(object, targetValue) {
  for (let key in object) {
    if (object.hasOwnProperty(key) && object[key] === targetValue) {
      return key;
    }
  }
  return null;
}

function tokenize(text, max_length) {
  text = text.toLowerCase();
  text = text.replace(/[!"#$%&()*+,-./:;<=>?@\[\\\]\^_`{|}~\t\n]/g, '')
  let split_text = text.split(' ');
  let tokens = [];
  split_text = split_text.slice(0, max_length)

  split_text.forEach(element => {
    if (tokenizer[element] != undefined) {
      tokens.push(tokenizer[element]);
    }
  });
  let i = 0;
  tokenized_text_segments = [];
  while (i + 50 < Math.max(tokens.length, 100)) {
    let new_slice = tokens.slice(i, i + 100);
    while (new_slice.length < max_length) {
      new_slice.push(0);
    }
    tokenized_text_segments.push(new_slice);
    i = i + 50;
  }

  return tokenized_text_segments;
}

async function predictText(answer) {
  return new Promise((resolve, reject) => {
    model.then(function (res) {
      // Preprocessing Textt
      const processedText = preprocessingText(answer);
      const tokenizedText = tokenize(processedText, 10);

      // predict text
      const predictResult = res.predict(tf.tensor2d(tokenizedText));
      const tagResultSequence = predictResult.argMax(-1).arraySync()[0];
      const tagResult = getKeyByValue(tagsClass, tagResultSequence);

      const repsonses = dataset.filter(d => d.tag === tagResult)[0].responses;
      const response = repsonses[Math.floor(Math.random() * repsonses.length)];

      resolve(response);
    }).catch(function (err) {
      reject(err);
    });
  });
}

const main = async () => {
  const result = await predictText('hai');
  console.log(result);
}

main();