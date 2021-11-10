import classes from './Layout.module.css';

function Layout(props) {
  return (
    <div className={classes.mainDiv}>
      {props.children}
    </div>
  );
}

export default Layout;