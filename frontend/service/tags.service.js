import { serverUrl } from "helpers/api";
import _ from "lodash";

async function getAll(token) {
  let data;
  try {
    data = await (
      await fetch(`${serverUrl}/tag/retriveTags`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "jwt-token": `${token}`
        },
      })
    ).json();
  } catch (err) {
    throw new Error(err);
  }
  if (data["statusCode"] !== 200 || data.length === 0) return [];
  data = data.map((tag) => {
    return {
      label: tag.tagName,
      value: tag.id
    }
  })
  return data;
}
/**
 * get tag names from an array of ids
 * @param {string} token jwt token of logged in user (can be fetched from `useUser`)
 * @param {number[]} ids array of tag ids
 * @returns
 */
async function getByIds(token, ids) {
  let data;
  try {
    data = await (
      await fetch(`${serverUrl}/tag/retriveTags`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          "jwt-token": `${token}`
        },
      })
    ).json();
  } catch (err) {
    throw new Error(err);
  }
  data = data.map((tag) => {
    return {
      label: tag.tagName,
      value: tag.id
    }
  })
  data = data.filter((tag) => !!(_.intersection([tag.value], ids).length > 0))
  return data;
}

async function createNew(newTags) {
  // @todo
  console.log("create new tags here", newTags);
}

export const tagsService = {
  getAll,
  getByIds,
  createNew
};
