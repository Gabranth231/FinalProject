import { Route, Switch } from 'react-router-dom';

import Layout from './components/layout/Layout';
import LoginPage from './components/pages/LoginPage'
import AdminPage from './components/pages/AdminPage'
import UserPage from './components/pages/UserPage'

function App() {
  return (
    <Layout>
      <Switch>
        <Route path='/' exact>
          <LoginPage />
        </Route>
        <Route path='/UserPage'>
          <UserPage />
        </Route>
        <Route path='/AdminPage'>
          <AdminPage />
        </Route>
      </Switch>
    </Layout>
  );
}

export default App;