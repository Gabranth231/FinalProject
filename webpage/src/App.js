import { Route, Switch } from 'react-router-dom';

import Layout from './components/layout/Layout';
import LoginPage from './components/pages/LoginPage'
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
      </Switch>
    </Layout>
  );
}

export default App;