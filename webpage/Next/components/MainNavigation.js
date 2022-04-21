import Link from "next/link";
import { Button, Grid } from "@nextui-org/react";

function MainNavigation(props) {
  return (
    <>
      <Grid.Container direction="vertical">
        <Grid md={12}>
          <Button.Group size="xl">
            <Link href="/UserPage">
              <Button>User Page</Button>
            </Link>
            <Link href="/TransactionsPage">
              <Button>Transactions</Button>
            </Link>
            <Link href="/TransferPage">
              <Button>Transfer</Button>
            </Link>
            <Link href="/">
              <Button>Logout</Button>
            </Link>
          </Button.Group>
        </Grid>
      </Grid.Container>
    </>
  );
}

export default MainNavigation;
