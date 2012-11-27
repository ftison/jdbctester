jdbctester
==========

Command line tool to check database connectivity

Info : Under development. Not already usable.

Syntaxe :
---------

jdbctest.sh --user <username> --passwd <password> --url <url> --driver <Jdbc Driver Class Name>


Result :
--------

Same return code as a Nagios Plugin.

0 = OK. Check is ok

1 = WARNING. The plugin was able to check the service, but it appeared to be above some "warning" threshold or did not appear to be working properly

2 = CRITICAL. The plugin detected that either the service was not running or it was above some "critical" threshold

3 = UNKNOWN. nvalid command line arguments were supplied to the plugin or low-level failures internal to the plugin (such as unable to fork, or open a tcp socket) that prevent it from performing the specified operation.
