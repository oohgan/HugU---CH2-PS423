const express = require('express')
const router = express.Router()
const userModel = require('../models/userModel')
const tf = require("@tensorflow/tfjs");
const tfnode = require("@tensorflow/tfjs-node");

router.get('/profile', async (req, res, next) => {
  const users = await userModel.find({ email: req.user.email })
  res.status(200);
  res.json(users[0]);
}
);

async function predictUsingModel(inputText){
  const handler = 'https://storage.googleapis.com/hugu-model-ml/model-chat.json';
  let model = await tf.loadLayersModel(handler);
  const input = tf.tensor2d([inputText], [1, 1]);
  const prediction = model.predict(input).dataSync();

  return prediction;
}

router.post('/chat-bot', async (req, res, next) => {
  const users = await userModel.find({ email: req.user.email })

  // Make predictions
  const inputText = req.body.text;
  const prediction = await predictUsingModel(inputText);

  // Process the output as needed
  res.status(200);
  res.json({ prediction });
}
);


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