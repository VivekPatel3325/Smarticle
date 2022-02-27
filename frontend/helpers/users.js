let data = require('data/users.json');
const fs = require('fs');

function create(u) {
  u.id = data.length ? Math.max(...data.map(x => x.id)) + 1 : 1;
  u.dateCreated = new Date().toISOString();
  u.dateUpdated = new Date().toISOString();
  data.push(u);
  save();
}

function update(id, user) {
  const u = data.find(x => x.id.toString() === id.toString());
  if (!u) return;
  u.dateUpdated = new Date().toISOString();
  Object.assign(data, user);
  save();
}

function _delete(id) {
  data = data.filter(x => x.id.toString() !== id.toString());
  save();
}

function save() {
  fs.writeFileSync('data/users.json', JSON.stringify(data, null, 4));
}

const users = {
  all: () => data,
  fromId: (id) => data.find(x => x.id.toString() === id.toString()),
  fromEmail: (email) => data.find(u => u.emailID.toString().toLowerCase() === email.toString().toLowerCase()),
  create,
  update,
  _delete
}

export default users;