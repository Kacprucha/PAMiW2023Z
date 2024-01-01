import { backendApi } from './backendApi'
import { bearerAuth } from './bearerAuth'

const noteClient = backendApi('/notes')

export const notesApi = {
  getAll (token) {
    console.log('Fetching notes')
    return noteClient.get('', {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  getById (id, token) {
    console.log('Get note', id)
    return noteClient.get(`/${id}`, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  getTop3ByUpdateDate(token) {
    console.log('Get 3 last updated notes')
    return noteClient.get(`/last-updated`, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  getTop3ByOwnerSortByUpdateDate(owner, token) {
    console.log('Get 3 last updated notes crted by specific owner: ', owner)
    return noteClient.get(`/author/3/${owner}`, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  getNotesByOwnerOrPublic(owner, token) {
    console.log('Get notes by owner: ', owner)
    return noteClient.get(`/ownerOrPublic/${owner}`, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  create (note, token) {
    console.log('Create note', note)
    return noteClient.post('', note, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  update (id, note, token) {
    console.log('Update note', id, note)
    return noteClient.put(`/${id}`, note, {
      headers: { Authorization: bearerAuth(token) }
    })
  },

  delete (id, token){
    console.log('Delete note', id)
    return noteClient.delete(`/${id}`, {
      headers: { Authorization: bearerAuth(token) }
    })
  }
}