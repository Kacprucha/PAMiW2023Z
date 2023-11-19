import { backendApi } from './backendApi'

const tasksClient = backendApi('/tasks')

export const tasksApi = {
  getAll () {
    console.log('Fetching tasks')
    return tasksClient.get('')
  },

  getById (id) {
    console.log('Get task', id)
    return tasksClient.get(`/${id}`)
  },

  create (task) {
    console.log('Create task', task)
    return tasksClient.post('', task)
  },

  update (id, task) {
    console.log('Update task', id, task)
    return tasksClient.put(`/${id}`, task)
  },

  delete (id) {
    console.log('Delete task', id)
    return tasksClient.delete(`/${id}`)
  }
}