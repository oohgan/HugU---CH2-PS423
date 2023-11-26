const express = require('express')
const router = express.Router()
const userModel = require('../models/userModel')

// Getting all
router.get('/', async (req, res) => {
  try {
    const users = await userModel.find()
    res.json(users)
  } catch (err) {
    res.status(500).json({ message: err.message })
  }
})

// Getting One
router.get('/:id', getUser, (req, res) => {
  res.json(res.user)
})

// Creating one
router.post('/', async (req, res) => {
  const user = new userModel({
    name: req.body.name,
    location: req.body.location
  })
  try {
    const newUser = await user.save()
    res.status(201).json(newUser)
  } catch (err) {
    res.status(400).json({ message: err.message })
  }
})

// Updating One
router.patch('/:id', getUser, async (req, res) => {
  if (req.body.name != null) {
    res.user.name = req.body.name
  }
  if (req.body.location != null) {
    res.user.location = req.body.location
  }
  try {
    const updatedUser = await res.user.save()
    res.json(updatedUser)
  } catch (err) {
    res.status(400).json({ message: err.message })
  }
})

// Deleting One
router.delete('/:id', getUser, async (req, res) => {
  try {
    await res.user.deleteOne()
    res.json({ message: 'Deleted user' })
  } catch (err) {
    res.status(500).json({ message: err.message })
  }
})

async function getUser(req, res, next) {
  let user
  try {
    user = await userModel.findById(req.params.id)
    if (user == null) {
      return res.status(404).json({ message: 'Cannot find user' })
    }
  } catch (err) {
    if (user == undefined) {
      return res.status(404).json({ message: "Cannot find user" })
    }
    return res.status(500).json({ message: err.message })
  }

  res.user = user
  next()
}

module.exports = router