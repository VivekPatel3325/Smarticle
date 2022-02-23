let data = require('../data/posts.json');
const posts = {
  onlyPublic: () => data.filter(a => a.public),
  fromAuthorOrPublic: (author) => data.filter((a) => (a.author.toLowerCase() === author.toLowerCase() || a.public)),
  ofTags: (tagsToCheck) => data.map(article => {
      return {
        id: article.id,
        tags: article.tags
      }
    }).map(({id, tags}) => {
      if (tags.filter(v => tagsToCheck.includes(v)).length > 0) {
        return id;
      }
      return;
    }).filter(v => v !== undefined),
  byId: (id) => data.find(v => v.id === id),
}

export default posts;
