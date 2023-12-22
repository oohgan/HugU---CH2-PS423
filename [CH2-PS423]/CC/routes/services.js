const express = require('express')
const router = express.Router()
const userModel = require('../models/userModel')
const tf = require("@tensorflow/tfjs");
const tfnode = require("@tensorflow/tfjs-node");
const fs = require('fs');

const handler = tfnode.io.fileSystem('D:/Aset/Bangkit Batch 5/Capstone Project/Project/helper/tfjs_model/model.json');
const model = tf.loadLayersModel(handler);
const tokenizerJson = fs.readFileSync('helper/tokenizer_chatbot_dict.json', 'utf8');
const tokenizer = JSON.parse(tokenizerJson);
const tagsClassJson = fs.readFileSync('helper/label_decoder.json');
const tagsClass = JSON.parse(tagsClassJson);
const datasetJson = fs.readFileSync('helper/Mental_Health_Conversational.json', 'utf8');
const dataset = JSON.parse(datasetJson);

router.get('/profile', async (req, res, next) => {
  const users = await userModel.find({ email: req.user.email })
  res.status(200);
  res.json(users[0]);
}
);

// Chat Bot 
const replace = [
  ['ibu', ['ibuku', 'mami', 'mama']],
  ['ayah', ['ayahku', 'papa', 'daddy']],
  ['saudara', ['adikku', 'adek', 'kakakku', 'koko', 'cece']],
  ['teman', ['temanku']],
  ['meninggal', ['mati']]
]

function preprocessingText(sentence) {
  const filteredWords = sentence.toLowerCase().replace(/[^\w\d\s]/g, '');
  const words = filteredWords.split(/\s+/);
  const clean_word = [];

  for (let word in words) {
    let replaced = False;
    for (let [replacement, target] of replace) {
      if (target.includes(word)) {
        clean_word.push(replacement);
        replaced = true;
      }
    } if (!replaced) {
      clean_word.push(word);
    }
  }
  return clean_word.join(' ');
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

router.post('/chat-bot', async (req, res, next) => {
  return new Promise((resolve, reject) => {
    model.then(function (res) {
      // preprocessing text
      const answer = req.body.text;
      const processedText = preprocessingText(answer);
      const tokenizedText = tokenize(processedText, 10);

      // predict text
      const predictResult = res.predict(tf.tensor2d(tokenizedText));
      const tagResultSequence = predictResult.argMax(-1).arraySync()[0];
      const tagResult = getKeyByValue(labelClass, tagResultSequence);

      const repsonses = dataset.filter(d => d.tag === tagResult)[0].responses;
      const response = repsonses[Math.floor(Math.random() * repsonses.length)];

      res.status(200);
      res.json({
        response: response,
      });


    }).catch(function (err) {
      reject(err);
    });
  });
  }
);

// Kuesioner
router.post('/kuesioner', async (req, res, next) => {

  const handler = tfnode.io.fileSystem('https://storage.googleapis.com/hugu-model-ml/model-kuesioner.json');
  let model = await tf.loadLayersModel(handler);

  const inputData = req.body.data;
  // Konversi data input menjadi tensor
  const inputTensor = tf.tensor2d([inputData]);
  // Lakukan prediksi dengan model
  const prediction = model.predict(inputTensor).dataSync();
  // Ambil label dari prediksi
  const labelIndex = prediction.indexOf(Math.max(...prediction));
  // Mengembalikan hasil prediksi sebagai respons JSON
  res.json({ result: getLabelFromIndex(labelIndex) });

  // Fungsi untuk mendapatkan label dari indeks
  function getLabelFromIndex(index) {
    // Sesuaikan dengan label yang Anda tetapkan
    const labels = ['Normal', 'Mid', 'Moderate', 'Severe', 'Extreamely Severe'];
    return labels;
  }
}
);


module.exports = router