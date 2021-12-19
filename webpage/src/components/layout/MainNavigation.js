import { Link } from 'react-router-dom';

import classes from './MainNavigation.module.css';

function MainNavigation() {
  return (
    <header className={classes.header}>
      <div className={classes.logo}>Final Year Project(Temp Nav bar)</div>
      <nav>
        <ul>
          <li>
            <Link to='/'>Login page</Link>
          </li>
          <li>
            <Link to='/UserPage'>User Page</Link>
          </li>
          <li>
            <Link to='/AdminPage'>Admin Page</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
}

export default MainNavigation;