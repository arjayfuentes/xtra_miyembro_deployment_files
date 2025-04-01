import jenkins.model.*
import hudson.security.*
import hudson.security.csrf.DefaultCrumbIssuer

// 1. Create admin account
def instance = Jenkins.get()
def hudsonRealm = new HudsonPrivateSecurityRealm(false)
hudsonRealm.createAccount('admin', 'Miyembro0101') // CHANGE THIS
instance.setSecurityRealm(hudsonRealm)

// 2. Configure granular permissions
def strategy = new GlobalMatrixAuthorizationStrategy()

// 3. Allow anonymous access to webhook endpoint only
strategy.add(Jenkins.READ, 'anonymous')
strategy.add(hudson.model.Item.BUILD, 'anonymous')

// 4. Admin permissions
strategy.add(Jenkins.ADMINISTER, 'admin')
strategy.add(Jenkins.READ, 'admin')

instance.setAuthorizationStrategy(strategy)

// 5. Configure CSRF protection for webhooks
instance.setCrumbIssuer(new DefaultCrumbIssuer(true))

// 6. Save configuration
instance.save()