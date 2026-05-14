# Logistic and Delivery Scheduling System
[LiveDemo](https://youtu.be/yTVHgnHJgdQ)

## Overview
This project implements a **Delivery Scheduling System** that solves the **Job Sequencing Problem** for an e-commerce/logistics company. <br />

The system helps decide which delivery orders to accept and in what sequence to maximize total profit while ensuring all scheduled deliveries meet their deadlines, by appling different scheduling algorithms to optimize delivery selection.

## Problem Statement
A logistics company receives multiple delivery orders daily. Each order has:
- **Deadline** – Must be delivered by a specific date
- **Profit**   – Based on order value and quantity
- **Priority** – Express or Standard delivery

With limited resources (single driver/vehicle), the company must select orders and schedule them to **maximize total profit** while meeting all deadlines.

## Assumptions and Constraints
1. Time Contrainst
   - Each delivery takes exactly 1 time unit (1 day) to complete
   - Deliveries are processed sequentially (one at a time)
   - Delivery deadline is measured in days from the order date
   - No partial deliveries allowed (order is either fully completed or skipped)

2. Resource Contrainst
   - Single delivery resource (one driver/truck)
   - No priority preemption (once scheduled, cannot be interrupted)

3. Business Rule
   - Express deliveries (high profit) have priority over standard deliveries 
   - All scheduled deliveries must meet their deadlines

## Dataset Description
| Parameter | Type | Description |
|-----------|-----------------|------------------|
| **DeliveryID** | String | Unique identifier for each order |
| **Item** | String | Product being delivered |
| **OrderDate** | Local Date | Date when order was placed |
| **Deadline** | Local Date | Latest date for delivery |
| **Sales** | Double | Selling price per unit (RM) |
| **Quantity** | Integer | Number of units ordered) |
| **Profit** | Double | Total profit = sales * quantity |

## Features
| Feature | Description |
|---------|-------------|
| **Load Data From File** | Load delivery data from external text file |
| **Random Generation** | Generate random delivery data with configurable size |
| **4 Scheduling Algorithms** | Compare different approaches to solve the problem |
| **Result Analysis** | View Selected, Unselected, Summary Report, Algorithm Performance Comparison |

## Algorithms Implemented
| Algorithm | Time Complexity |
|-----------|-----------------|
| **Greedy Algorithm** | $O(n^2)$ |  
| **Task Priority Scheduling (Weighted)** | O(n log n) |  
| **Dynamic Programming** | O(n*D) |  
| **Earliest Deadline First (EDF)** |  O(n log n) | 

## Performance Comparison Table
| Aspect | Greedy Algorithm | Task Priority Scheduling (Weighted) | Dynamic Programming | Earliest Deadline First (EDF) | 
|-----------|-----------------|------------------|------------------|------------------|
| **Core Strategy** | Highest profit first | Highest profit first with deadline-based scheduling | Break complex problem into subproblems and solve the subproblems | Earliest deadline first |
| **Time Complexity** | $O(n^2)$ | O(n log n + n*d) | O(n*D)| O(n log n)|
| **Deadline Guarantee** | No | Partial | Yes | Yes |
| **Profit Maximizied** | Yes | Yes | Yes | No |
| **Scalability** | Poor | Moderate | Moderate | Excellent |
| **Implementation Complexity** | Low | Moderate | High | Low |
| **Speed** | Slow for larger input size, n | Fast | Moderate | Fastest |

#### _Which Algorithm Win? Performs the Best?_
| If priority is... | Choose... | Reason | 
|-----------|-----------------|------------------|
| **Meeting deadlines at all costs** | EDF | Guarantees every scheduled job meets its deadline | 
| **Maximum profit (small dataset)** | DP | Guarantees 100% optimal profit | 
| **Flexible priority system** | Task Priority | Prioritises high profit jobs while respecting deadline constraints |
| **Quick and simple solution** | EDF and Greedy | Minimal code, and easy to understand | 
| **Multi-factor decisions** | Task Priority | Can incorporate profit, deadlines, customer value, distance, and etc | 

## System Design
- DeliveryStrategy<E> Interface
  - Provide scheduling behavior, which allows different algorithm implementations
- AbstractDeliveryStrategy<E>
  - Provide common attributes and methods (selected/unselected/totalProfit) that are shared by all scheduling algorithms to  avoid code duplication.
- Concrete Classes
  - Implement specific algorithms, such as Greedy Algorithm, Task Priority Scheduling (Weighted), Dynamic Programming, Earliest Deadline First (EDF)
- Model Class
  - This package contains Delivery Class, which represents the core data entity.
- View Layer
  - This package contains DisplayView Class, which handle all user interface and output display
- Controller Layer
  - DeliveryController Class orchestrates program flow and coordinates between Model and View. In this class, it doesn't have display logic, only contains flow coordination.
- Utility Layer
  - This package contains 2 different method to input data, such as FileLoader and RandomGenerator
- TestDeliveryApp Class
  - Acts as the entry point

## Project Structure
```bash
DeliverySchedulingSystem/
│
├── src/
│   ├── model/
│   │   └── Delivery.java
│   │
│   ├── strategy/
│   │   ├── DeliveryStrategy.java
│   │   ├── AbstractDeliveryStrategy.java
│   │   ├── GreedyAlgorithm.java
│   │   ├── TPSWeightedAlgorithm.java
│   │   ├── DPAlgorithm.java
│   │   └── EDFAlgorithm.java
│   │
│   ├── view/
│   │   └── DisplayView.java
│   │
│   ├── controller/
│   │   └── DeliveryController.java
│   │   └── TestDeliveryApp.java
│   │
│   ├── util/
│   │   ├── FileLoader.java
│   │   └── RandomGenerator.java
│   │  
│   │
│   ├── delivery.txt
│   └── item.txt
```

## Sample Input
- Load Data From Text File <br />
The system reads delivery data from `src/data/delivery.txt` with the following format:
```
Order#1,SmartPhone,2026-03-20,2026-03-22,3000,1,3000
Order#2,Keyboard,2026-03-20,2026-03-21,500,2,1000
Order#3,Laptop,2026-03-21,2026-03-23,2000,1,2000
Order#4,Powerbank,2026-03-21,2026-03-22,20,5,100
Order#5,Gaming Mouse,2026-03-22,2026-03-24,200,3,600
Order#6,Whole Chicken,2026-03-22,2026-03-23,12,4,48
Order#7,Keyboard,2026-03-23,2026-03-25,8,15,120
```
