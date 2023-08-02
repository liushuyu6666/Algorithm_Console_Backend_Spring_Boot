# Getting Started
We will first consider to deploy it on local.


# Java Syntax
## Collectors
We use this code snippet to find the maximum value upon the `rank` value grouped by the `ancestorId`.
    ```java
        ancestors.stream().collect(
            Collectors.groupingBy(
                Ancestor::getAncestorId,
                Collectors.mapping(Ancestor::getRank, Collectors.maxBy(Comparator.naturalOrder()))
            )
        );
    ```
1. `Collectors.groupingBy` maps each `Ancestor` object to its associated `ancestorId` value, resulting in the creation of multiple groups based on these `ancestorId` values. Subsequently, within each group, a secondary operation (implemented by another `Collector` called `mapping`) is applied."
2. `Collectors.mapping` maps each `Ancestor` object to its associated `rank` value and applies a subsequent operation (implemented by the third `Collector` named `maxBy`) to find the highest rank value within each group.

