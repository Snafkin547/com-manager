# com-manager

# Desired features to add
 - Optimizing ship distribution to each port depending on clients' short-term cargo demand/storage capacity
 - Stowage plan optimizer
 - Commodity position management including Physical, futures, options
 - Trade Contract generator
 - Market Platform for commodity sellers/buyers and ship sellers/buyers to place order

# Description
com-manager will provide a user-friendly interface for commodity traders and supply-chain managers. This app is for you if you are responsible for decision making in commodity trade, procurement, and cargoes allocations to transportations and storages.

Presently, users can register cargoes information such as type of items, size, delivery/expiriy date, transportations in their hand like ships and trucks, including their costs per ton, and storage capacities and costs. 
As you can see even by far, there are many variables in one string of logistics chain. 
It would expectedly be a lot more complicated as the numbers of chains grow and users have more optionalities.

com-manager is to serve for such needs by handy application with the following key functions

# Key Features
The following functions will be deployed. Users can use this app in the numbered sequence: register logistics elements, manage database, and run the optimization algorithm 

## 1) Registration 
   This function is the data entry point for users to create the database. Users can input the following information:
   - Ship Information (Name, Size, ETA, Cost, Status) : [Ship Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/Ship.java#L3)
   - Cargo Information (Item, Size, Cost, Expiry, Status) : [Cargo Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/Cargo.java#L3)
   - Storage Information (Name, Capacity, Location, Cost, Acceptable Items, Status) : [Storage Class](https://github.com/Snafkin547/logi-ops/blob/483f990c4fe8cda8742c8a2769e04768cf147e8b/src/cpw/Storage.java#L6)

## 2) Search Algorithm
   This function is to manage/view users' database. Users can set query criteria, retrieve relevant dataset, and view/modify it
   - Ship Search (By Name, Size, ETA, Cost, Availability) : [dbOrganizer Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/dbOrganizer.java#L5)
   - Cargo Search (By Item, Size, Cost, Expiry, Availability) :[dbOrganizer Class](https://github.com/Snafkin547/logi-ops/blob/4b18dd5785d1c90119b4a63ee7cc39aa05a294bd/src/cpw/dbOrganizer.java#L30)

##  3) Logistics optimizer
   This function optimizes logistics combinations of Ships, Trucks, Cargoes, and Storages. Users can calibrate and find the best match in the whole open-logistics positions, or just some part of the positions, like linking only ships and cargoes that are available and need to be sent within this months.
   
   - Whole(Cargoes/Ships/Storage) logistics Optimizer : [CargoMatching class](https://github.com/Snafkin547/logi-ops/blob/4b18dd5785d1c90119b4a63ee7cc39aa05a294bd/src/cpw/CargoMatching.java#L324)

   - Cargoes - Ships Optimizer : [CargoMatching Class](https://github.com/Snafkin547/logi-ops/blob/351918292e8e8bc30a5e5a9aff9a3ebb563af90d/src/cpw/CargoMatching.java#L6)

com-manager will give users an up-to-date optimized logistics recommendation.

Users will be asked to provide Logi-Ops logistics requirements, such as:
1.	Ships’ information (Name, Cost, estimated time of arrival (ETA), if cargo on board)
2.	Cargo information (Name, Item, Size, time of arrival required (ETAR), if on a ship)
3.	Storage information (Name, Acceptable items, open space for each item, daily cost)

If users input the following sets of information to com-manager:
### 1.	Ships 

 ![image](https://user-images.githubusercontent.com/62607343/138567937-00f9dbe8-d401-48bc-840e-d6cc4deb23a1.png)

### 2.	Cargos 

 ![image](https://user-images.githubusercontent.com/62607343/138567938-4967d352-14c3-46ce-a9f1-cd4a6fe57955.png)

### 3.	Storages

 ![image](https://user-images.githubusercontent.com/62607343/138567943-f0dd6799-762f-44af-b3aa-c35c3eb596dc.png)


### com-manager will explore optimization in the following steps:

#### Ship – Cargo Matching
1.	com-manager will ignore Ship Z and Cargo C since they already carry a cargo or are on board 
2.	Between pairs of X-A/Y-B and X-B/Y-A, the latter is better and do-able, because the former costs $400k (100*3,000+50*2,000) whereas the latter costs only $350k (50*3,000+100*2,000), they make required ETAs and have enough sizes

#### Cargo- Storage Matching
1.	Next, it will compute costs and decide where these cargoes should be accommodated
2.	In the simple math, the combination of A-3/B-1/C-2 costs the cheapest, $55k/Day(3,000*5+2,000*10+1,000*20), however, this is not feasible because cargo A size(3,000) is bigger than open space in storage 3
3.	com-manager would then consider the next best combination, A-1/B-3/C-2. This is not acceptable either because Storage 3 does not accept Paper, Cargo B. 
4.	Finally, the latest best is narrowed down to either A-2/B-1/C-3 or A-3/B-2/C-1, which cost $65k. For the same reason as earlier, only the former is acceptable. Therefore, com-manager will recommend A-2/B-1/C-3 combination.

# Dependeies
![image](https://user-images.githubusercontent.com/62607343/138567911-b0a29e05-cbf4-4476-baf2-9b000f1658af.png)

