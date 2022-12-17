const mongoose = require('mongoose')
mongoose.Promise = global.Promise
mongoose.set('strictQuery', false)
module.exports = mongoose.connect('mongodb://localhost/todo')
