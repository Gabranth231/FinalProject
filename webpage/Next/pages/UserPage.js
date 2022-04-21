import Users from '../components/Users';
import Layout from '../components/layout2';

export default function UserPage() {
  return (
    <Users/> 
  )
}
UserPage.getLayout = function getLayout(page) {
  return (
    <Layout>
      {page}
    </Layout>
  )
}
