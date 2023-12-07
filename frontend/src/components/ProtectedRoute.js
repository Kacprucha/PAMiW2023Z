import { Outlet } from 'react-router-dom'
import { useAuth } from 'react-oidc-context'
import { Loading } from './Loading'

export const ProtectedRoute = () => {
  const auth = useAuth()

  if (auth.isLoading) {
    return Loading
  } else if (!auth.isAuthenticated) {
    return auth.signinRedirect()
  }

  return <Outlet />
}