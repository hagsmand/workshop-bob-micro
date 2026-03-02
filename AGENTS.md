# Performance Issue Rules
- In case you have been asked to fix performance issue. Please consider easiest and fastest solution higher than consider the long-term fix. Most of the time, the long-term fix is not quick enough to answer SLA. 
- For example, if the issue is caused by the database, the fastest solution is to add more database instances. However, the long-term fix is to improve the database performance.
- Another example is that if the server reach the CPU limit, the fastest solution is to add more servers or implement cache. However, the long-term fix is to improve the server performance.

# Fix UI for Performance Issue
- When you have to fix the UI to ensure the Shipping API really hits the cache using spring-boot-starter-cache to store cache in Spring context. Edit the UI that let the user see cache that stored. You MUST ensure that the size of the component that display cache is clear to see for the user, for example, the grid-column should be 12 or 6. Write unit test to ensure the cache is work. Note that this project run with Podman.
