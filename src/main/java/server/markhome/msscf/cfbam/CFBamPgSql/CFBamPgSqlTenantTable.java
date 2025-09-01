// Description: Java 11 PostgreSQL Jdbc DbIO implementation for Tenant.

/*
 *	server.markhome.msscf.CFBam
 *
 *	Copyright (c) 2020-2025 Mark Stephen Sobkow
 *	
 *
 *	Manufactured by MSS Code Factory 2.13
 */

package server.markhome.msscf.cfbam.CFBamPgSql;

import java.math.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.msscf.msscf.cflib.CFLib.*;
import server.markhome.msscf.cfsec.CFSec.*;
import server.markhome.msscf.cfint.CFInt.*;
import server.markhome.msscf.cfbam.CFBam.*;
import server.markhome.msscf.cfsec.CFSecObj.*;
import server.markhome.msscf.cfint.CFIntObj.*;
import server.markhome.msscf.cfbam.CFBamObj.*;

/*
 *	CFBamPgSqlTenantTable PostgreSQL Jdbc DbIO implementation
 *	for Tenant.
 */
public class CFBamPgSqlTenantTable
	implements ICFBamTenantTable
{
	private CFBamPgSqlSchema schema;
	protected PreparedStatement stmtReadBuffByPKey = null;
	protected PreparedStatement stmtReadBuffAll = null;
	protected PreparedStatement stmtPageBuffAll = null;
	protected PreparedStatement stmtLockBuffByPKey = null;
	protected PreparedStatement stmtCreateByPKey = null;
	protected PreparedStatement stmtUpdateByPKey = null;
	protected PreparedStatement stmtDeleteByPKey = null;
	protected PreparedStatement stmtReadAllBuff = null;
	protected PreparedStatement stmtReadBuffByIdIdx = null;
	protected PreparedStatement stmtReadBuffByClusterIdx = null;
	protected PreparedStatement stmtReadBuffByUNameIdx = null;
	protected PreparedStatement stmtPageBuffByClusterIdx = null;
	protected PreparedStatement stmtDeleteByIdIdx = null;
	protected PreparedStatement stmtDeleteByClusterIdx = null;
	protected PreparedStatement stmtDeleteByUNameIdx = null;
	protected PreparedStatement stmtSelectNextTSecGroupIdGen = null;
	protected PreparedStatement stmtSelectNextTSecGrpIncIdGen = null;
	protected PreparedStatement stmtSelectNextTSecGrpMembIdGen = null;
	protected PreparedStatement stmtSelectNextMajorVersionIdGen = null;
	protected PreparedStatement stmtSelectNextMinorVersionIdGen = null;
	protected PreparedStatement stmtSelectNextSubProjectIdGen = null;
	protected PreparedStatement stmtSelectNextTldIdGen = null;
	protected PreparedStatement stmtSelectNextTopDomainIdGen = null;
	protected PreparedStatement stmtSelectNextTopProjectIdGen = null;
	protected PreparedStatement stmtSelectNextChainIdGen = null;
	protected PreparedStatement stmtSelectNextEnumTagIdGen = null;
	protected PreparedStatement stmtSelectNextIndexColIdGen = null;
	protected PreparedStatement stmtSelectNextParamIdGen = null;
	protected PreparedStatement stmtSelectNextRelationColIdGen = null;
	protected PreparedStatement stmtSelectNextScopeIdGen = null;
	protected PreparedStatement stmtSelectNextValueIdGen = null;

	public CFBamPgSqlTenantTable( CFBamPgSqlSchema argSchema ) {
		schema = argSchema;
	}

	public int nextTSecGroupIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextTSecGroupIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_tsecgroupidgen("
			+		"?" + " ) as NextTSecGroupIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextTSecGroupIdGen == null ) {
				stmtSelectNextTSecGroupIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			int nextId;
			int argIdx = 1;
			stmtSelectNextTSecGroupIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextTSecGroupIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getInt( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextTSecGroupIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_tsecgroupidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public int nextTSecGroupIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		int retval = nextTSecGroupIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextTSecGrpIncIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextTSecGrpIncIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_tsecgrpincidgen("
			+		"?" + " ) as NextTSecGrpIncIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextTSecGrpIncIdGen == null ) {
				stmtSelectNextTSecGrpIncIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextTSecGrpIncIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextTSecGrpIncIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextTSecGrpIncIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_tsecgrpincidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextTSecGrpIncIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextTSecGrpIncIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextTSecGrpMembIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextTSecGrpMembIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_tsecgrpmembidgen("
			+		"?" + " ) as NextTSecGrpMembIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextTSecGrpMembIdGen == null ) {
				stmtSelectNextTSecGrpMembIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextTSecGrpMembIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextTSecGrpMembIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextTSecGrpMembIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_tsecgrpmembidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextTSecGrpMembIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextTSecGrpMembIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextMajorVersionIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextMajorVersionIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_majorversionidgen("
			+		"?" + " ) as NextMajorVersionIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextMajorVersionIdGen == null ) {
				stmtSelectNextMajorVersionIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextMajorVersionIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextMajorVersionIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextMajorVersionIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_majorversionidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextMajorVersionIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextMajorVersionIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextMinorVersionIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextMinorVersionIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_minorversionidgen("
			+		"?" + " ) as NextMinorVersionIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextMinorVersionIdGen == null ) {
				stmtSelectNextMinorVersionIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextMinorVersionIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextMinorVersionIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextMinorVersionIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_minorversionidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextMinorVersionIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextMinorVersionIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextSubProjectIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextSubProjectIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_subprojectidgen("
			+		"?" + " ) as NextSubProjectIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextSubProjectIdGen == null ) {
				stmtSelectNextSubProjectIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextSubProjectIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextSubProjectIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextSubProjectIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_subprojectidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextSubProjectIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextSubProjectIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextTldIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextTldIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_tldidgen("
			+		"?" + " ) as NextTldIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextTldIdGen == null ) {
				stmtSelectNextTldIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextTldIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextTldIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextTldIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_tldidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextTldIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextTldIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextTopDomainIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextTopDomainIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_topdomainidgen("
			+		"?" + " ) as NextTopDomainIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextTopDomainIdGen == null ) {
				stmtSelectNextTopDomainIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextTopDomainIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextTopDomainIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextTopDomainIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_topdomainidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextTopDomainIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextTopDomainIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextTopProjectIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextTopProjectIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_topprojectidgen("
			+		"?" + " ) as NextTopProjectIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextTopProjectIdGen == null ) {
				stmtSelectNextTopProjectIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextTopProjectIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextTopProjectIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextTopProjectIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_topprojectidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextTopProjectIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextTopProjectIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextChainIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextChainIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_chainidgen("
			+		"?" + " ) as NextChainIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextChainIdGen == null ) {
				stmtSelectNextChainIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextChainIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextChainIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextChainIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_chainidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextChainIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextChainIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextEnumTagIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextEnumTagIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_enumtagidgen("
			+		"?" + " ) as NextEnumTagIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextEnumTagIdGen == null ) {
				stmtSelectNextEnumTagIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextEnumTagIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextEnumTagIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextEnumTagIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_enumtagidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextEnumTagIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextEnumTagIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextIndexColIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextIndexColIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_indexcolidgen("
			+		"?" + " ) as NextIndexColIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextIndexColIdGen == null ) {
				stmtSelectNextIndexColIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextIndexColIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextIndexColIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextIndexColIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_indexcolidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextIndexColIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextIndexColIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextParamIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextParamIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_paramidgen("
			+		"?" + " ) as NextParamIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextParamIdGen == null ) {
				stmtSelectNextParamIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextParamIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextParamIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextParamIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_paramidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextParamIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextParamIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextRelationColIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextRelationColIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_relationcolidgen("
			+		"?" + " ) as NextRelationColIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextRelationColIdGen == null ) {
				stmtSelectNextRelationColIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextRelationColIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextRelationColIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextRelationColIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_relationcolidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextRelationColIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextRelationColIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextScopeIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextScopeIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_scopeidgen("
			+		"?" + " ) as NextScopeIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextScopeIdGen == null ) {
				stmtSelectNextScopeIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextScopeIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextScopeIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextScopeIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_scopeidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextScopeIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextScopeIdGen( Authorization, pkey );
		return( retval );
	}

	public long nextValueIdGen( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "nextValueIdGen";
		String sqlSelectNext =
				"SELECT " + schema.getLowerDbSchemaName() + ".sp_next_valueidgen("
			+		"?" + " ) as NextValueIdGen";

		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet rsSelect = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();

			if( stmtSelectNextValueIdGen == null ) {
				stmtSelectNextValueIdGen = cnx.prepareStatement( sqlSelectNext );
			}
			long nextId;
			int argIdx = 1;
			stmtSelectNextValueIdGen.setLong( argIdx++, Id );
			rsSelect = stmtSelectNextValueIdGen.executeQuery();
			if( rsSelect.next() ) {
				nextId = rsSelect.getLong( 1 );
				if( rsSelect.wasNull() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"NextValueIdGen cannot be null!" );
				}
				if( rsSelect.next() ) {
					rsSelect.last();
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record select response, " + rsSelect.getRow() + " rows selected" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 result row to be returned by sp_next_valueidgen(), not 0" );
			}
			return( nextId );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( rsSelect != null ) {
				try {
					rsSelect.close();
				}
				catch( SQLException e ) {
				}
				rsSelect = null;
			}
		}
	}

	public long nextValueIdGen( CFSecAuthorization Authorization,
		long argId )
	{
		CFSecTenantPKey pkey = schema.getFactoryTenant().newPKey();
		pkey.setRequiredId( argId );
		long retval = nextValueIdGen( Authorization, pkey );
		return( retval );
	}

	public void createTenant( CFSecAuthorization Authorization,
		CFSecTenantBuff Buff )
	{
		final String S_ProcName = "createTenant";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			String TenantName = Buff.getRequiredTenantName();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_create_tenant( ?, ?, ?, ?, ?, ?" + ", "
				+		"?" + ", "
				+		"?" + " )";
			if( stmtCreateByPKey == null ) {
				stmtCreateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtCreateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtCreateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtCreateByPKey.setString( argIdx++, "a015" );
			stmtCreateByPKey.setLong( argIdx++, ClusterId );
			stmtCreateByPKey.setString( argIdx++, TenantName );
			resultSet = stmtCreateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTenantBuff createdBuff = unpackTenantResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredClusterId( createdBuff.getRequiredClusterId() );
				Buff.setRequiredId( createdBuff.getRequiredId() );
				Buff.setRequiredTenantName( createdBuff.getRequiredTenantName() );
				Buff.setRequiredRevision( createdBuff.getRequiredRevision() );
				Buff.setCreatedByUserId( createdBuff.getCreatedByUserId() );
				Buff.setCreatedAt( createdBuff.getCreatedAt() );
				Buff.setUpdatedByUserId( createdBuff.getUpdatedByUserId() );
				Buff.setUpdatedAt( createdBuff.getUpdatedAt() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public String S_sqlSelectTenantDistinctClassCode = null;

	public String getSqlSelectTenantDistinctClassCode() {
		if( S_sqlSelectTenantDistinctClassCode == null ) {
			S_sqlSelectTenantDistinctClassCode =
					"SELECT "
					+		"DISTINCT a015.ClassCode "
					+	"FROM " + schema.getLowerDbSchemaName() + ".tenant AS a015 ";
		}
		return( S_sqlSelectTenantDistinctClassCode );
	}

	public String S_sqlSelectTenantBuff = null;

	public String getSqlSelectTenantBuff() {
		if( S_sqlSelectTenantBuff == null ) {
			S_sqlSelectTenantBuff =
					"SELECT "
					+		"a015.Id, "
					+		"a015.ClusterId, "
					+		"a015.TenantName, "
					+		"a015.Revision "
				+	"FROM " + schema.getLowerDbSchemaName() + ".tenant AS a015 ";
		}
		return( S_sqlSelectTenantBuff );
	}

	protected CFSecTenantBuff unpackTenantResultSetToBuff( ResultSet resultSet )
	throws SQLException
	{
		final String S_ProcName = "unpackTenantResultSetToBuff";
		int idxcol = 1;
		CFSecTenantBuff buff = schema.getFactoryTenant().newBuff();
		{
			String colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedByUserId( null );
			}
			else {
				buff.setCreatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setCreatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setCreatedAt( null );
			}
			else {
				buff.setCreatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedByUserId( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedByUserId( null );
			}
			else {
				buff.setUpdatedByUserId( UUID.fromString( colString ) );
			}
			idxcol ++;

			colString = resultSet.getString( idxcol );
			if( resultSet.wasNull() ) {
				buff.setUpdatedAt( null );
			}
			else if( ( colString == null ) || ( colString.length() <= 0 ) ) {
				buff.setUpdatedAt( null );
			}
			else {
				buff.setUpdatedAt( CFBamPgSqlSchema.convertTimestampString( colString ) );
			}
			idxcol++;
		}
		buff.setRequiredClusterId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredId( resultSet.getLong( idxcol ) );
		idxcol++;
		buff.setRequiredTenantName( resultSet.getString( idxcol ) );
		idxcol++;

		buff.setRequiredRevision( resultSet.getInt( idxcol ) );
		return( buff );
	}

	public CFSecTenantBuff readDerived( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "readDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTenantBuff buff;

		buff = readBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecTenantBuff lockDerived( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "lockDerived";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTenantBuff buff;

		buff = lockBuff( Authorization, PKey );
		return( buff );
	}

	public CFSecTenantBuff[] readAllDerived( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllDerived";
		CFSecTenantBuff[] buffArray;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buffArray = readAllBuff( Authorization );
		return( buffArray );
	}

	public CFSecTenantBuff readDerivedByIdIdx( CFSecAuthorization Authorization,
		long Id )
	{
		final String S_ProcName = "CFBamPgSqlTenantTable.readDerivedByIdIdx() ";
		CFSecTenantBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByIdIdx( Authorization,
				Id );
		return( buff );
	}

	public CFSecTenantBuff[] readDerivedByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readDerivedByClusterIdx";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		CFSecTenantBuff[] buffList = readBuffByClusterIdx( Authorization,
				ClusterId );
		return( buffList );

	}

	public CFSecTenantBuff readDerivedByUNameIdx( CFSecAuthorization Authorization,
		long ClusterId,
		String TenantName )
	{
		final String S_ProcName = "CFBamPgSqlTenantTable.readDerivedByUNameIdx() ";
		CFSecTenantBuff buff;
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		buff = readBuffByUNameIdx( Authorization,
				ClusterId,
				TenantName );
		return( buff );
	}

	public CFSecTenantBuff readBuff( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "readBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tenant( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByPKey == null ) {
				stmtReadBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecTenantBuff lockBuff( CFSecAuthorization Authorization,
		CFSecTenantPKey PKey )
	{
		final String S_ProcName = "lockBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = PKey.getRequiredId();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_lock_tenant( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtLockBuffByPKey == null ) {
				stmtLockBuffByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtLockBuffByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtLockBuffByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtLockBuffByPKey.setLong( argIdx++, Id );
			resultSet = stmtLockBuffByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecTenantBuff[] readAllBuff( CFSecAuthorization Authorization ) {
		final String S_ProcName = "readAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tenant_all( ?, ?, ?, ?, ? )";
			if( stmtReadAllBuff == null ) {
				stmtReadAllBuff = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadAllBuff.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadAllBuff.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			resultSet = stmtReadAllBuff.executeQuery();
			List<CFSecTenantBuff> buffList = new LinkedList<CFSecTenantBuff>();
			while( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTenantBuff[] retBuff = new CFSecTenantBuff[ buffList.size() ];
			Iterator<CFSecTenantBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	/**
	 *	Read a page of all the specific Tenant buffer instances.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@return All the specific Tenant instances in the database accessible for the Authorization.
	 */
	public CFSecTenantBuff[] pageAllBuff( CFSecAuthorization Authorization,
		Long priorId )
	{
		final String S_ProcName = "pageAllBuff";
		if( ! schema.isTransactionOpen() ) {
			throw new CFLibUsageException( getClass(),
				S_ProcName,
				"Transaction not open" );
		}
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_tenant_all( ?, ?, ?, ?, ?, ? )";
			if( stmtPageBuffAll == null ) {
				stmtPageBuffAll = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffAll.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffAll.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			if( priorId != null ) {
				stmtPageBuffAll.setLong( argIdx++, priorId.longValue() );
			}
			else {
				stmtPageBuffAll.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffAll.executeQuery();
			List<CFSecTenantBuff> buffList = new LinkedList<CFSecTenantBuff>();
			while( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTenantBuff[] retBuff = new CFSecTenantBuff[ buffList.size() ];
			Iterator<CFSecTenantBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecTenantBuff readBuffByIdIdx( CFSecAuthorization Authorization,
		long Id )
	{
		final String S_ProcName = "readBuffByIdIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tenant_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByIdIdx == null ) {
				stmtReadBuffByIdIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByIdIdx.setLong( argIdx++, Id );
			resultSet = stmtReadBuffByIdIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecTenantBuff[] readBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId )
	{
		final String S_ProcName = "readBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tenant_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByClusterIdx == null ) {
				stmtReadBuffByClusterIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByClusterIdx.setLong( argIdx++, ClusterId );
			resultSet = stmtReadBuffByClusterIdx.executeQuery();
			List<CFSecTenantBuff> buffList = new LinkedList<CFSecTenantBuff>();
			while( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTenantBuff[] retBuff = new CFSecTenantBuff[ buffList.size() ];
			Iterator<CFSecTenantBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public CFSecTenantBuff readBuffByUNameIdx( CFSecAuthorization Authorization,
		long ClusterId,
		String TenantName )
	{
		final String S_ProcName = "readBuffByUNameIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_read_tenant_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtReadBuffByUNameIdx == null ) {
				stmtReadBuffByUNameIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtReadBuffByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtReadBuffByUNameIdx.setLong( argIdx++, ClusterId );
			stmtReadBuffByUNameIdx.setString( argIdx++, TenantName );
			resultSet = stmtReadBuffByUNameIdx.executeQuery();
			if( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				return( buff );
			}
			else {
				return( null );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	/**
	 *	Read a page array of the specific Tenant buffer instances identified by the duplicate key ClusterIdx.
	 *
	 *	@param	Authorization	The session authorization information.
	 *
	 *	@param	argClusterId	The Tenant key attribute of the instance generating the id.
	 *
	 *	@return An array of derived buffer instances for the specified key, potentially with 0 elements in the set.
	 *
	 *	@throws	CFLibNotSupportedException thrown by client-side implementations.
	 */
	public CFSecTenantBuff[] pageBuffByClusterIdx( CFSecAuthorization Authorization,
		long ClusterId,
		Long priorId )
	{
		final String S_ProcName = "pageBuffByClusterIdx";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			String sql = "SELECT * FROM " + schema.getLowerDbSchemaName() + ".sp_page_tenant_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " )";
			if( stmtPageBuffByClusterIdx == null ) {
				stmtPageBuffByClusterIdx = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtPageBuffByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtPageBuffByClusterIdx.setLong( argIdx++, ClusterId );
			if( priorId != null ) {
				stmtPageBuffByClusterIdx.setLong( argIdx++, priorId.longValue() );
			}
			else {
				stmtPageBuffByClusterIdx.setNull( argIdx++, java.sql.Types.BIGINT );
			}
			resultSet = stmtPageBuffByClusterIdx.executeQuery();
			List<CFSecTenantBuff> buffList = new LinkedList<CFSecTenantBuff>();
			while( resultSet.next() ) {
				CFSecTenantBuff buff = unpackTenantResultSetToBuff( resultSet );
				buffList.add( buff );
			}
			int idx = 0;
			CFSecTenantBuff[] retBuff = new CFSecTenantBuff[ buffList.size() ];
			Iterator<CFSecTenantBuff> iter = buffList.iterator();
			while( iter.hasNext() ) {
				retBuff[idx++] = iter.next();
			}
			return( retBuff );
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void updateTenant( CFSecAuthorization Authorization,
		CFSecTenantBuff Buff )
	{
		final String S_ProcName = "updateTenant";
		ResultSet resultSet = null;
		try {
			long ClusterId = Buff.getRequiredClusterId();
			long Id = Buff.getRequiredId();
			String TenantName = Buff.getRequiredTenantName();
			int Revision = Buff.getRequiredRevision();
			Connection cnx = schema.getCnx();
			String sql =
				"select * from " + schema.getLowerDbSchemaName() + ".sp_update_tenant( ?, ?, ?, ?, ?, ?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + ", "
						+	"?" + " )";
			if( stmtUpdateByPKey == null ) {
				stmtUpdateByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtUpdateByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtUpdateByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtUpdateByPKey.setString( argIdx++, "a015" );
			stmtUpdateByPKey.setLong( argIdx++, ClusterId );
			stmtUpdateByPKey.setLong( argIdx++, Id );
			stmtUpdateByPKey.setString( argIdx++, TenantName );
			stmtUpdateByPKey.setInt( argIdx++, Revision );
			resultSet = stmtUpdateByPKey.executeQuery();
			if( resultSet.next() ) {
				CFSecTenantBuff updatedBuff = unpackTenantResultSetToBuff( resultSet );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
				Buff.setRequiredClusterId( updatedBuff.getRequiredClusterId() );
				Buff.setRequiredTenantName( updatedBuff.getRequiredTenantName() );
				Buff.setRequiredRevision( updatedBuff.getRequiredRevision() );
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected a single-record response, " + resultSet.getRow() + " rows selected" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteTenant( CFSecAuthorization Authorization,
		CFSecTenantBuff Buff )
	{
		final String S_ProcName = "deleteTenant";
		ResultSet resultSet = null;
		try {
			Connection cnx = schema.getCnx();
			long Id = Buff.getRequiredId();

			String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tenant( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
			if( stmtDeleteByPKey == null ) {
				stmtDeleteByPKey = cnx.prepareStatement( sql );
			}
			int argIdx = 1;
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
			stmtDeleteByPKey.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
			stmtDeleteByPKey.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
			stmtDeleteByPKey.setLong( argIdx++, Id );
			stmtDeleteByPKey.setInt( argIdx++, Buff.getRequiredRevision() );;
			resultSet = stmtDeleteByPKey.executeQuery();
			if( resultSet.next() ) {
				boolean deleteFlag = resultSet.getBoolean( 1 );
				if( resultSet.next() ) {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Did not expect multi-record response" );
				}
			}
			else {
				throw new CFLibRuntimeException( getClass(),
					S_ProcName,
					"Expected 1 record result set to be returned by delete, not 0 rows" );
			}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteTenantByIdIdx( CFSecAuthorization Authorization,
		long argId )
	{
		final String S_ProcName = "deleteTenantByIdIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tenant_by_ididx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByIdIdx == null ) {
					stmtDeleteByIdIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByIdIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByIdIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByIdIdx.setLong( argIdx++, argId );
				resultSet = stmtDeleteByIdIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
			}
			catch( SQLException e ) {
				throw new CFLibDbException( getClass(),
					S_ProcName,
					e );
			}
			finally {
				if( resultSet != null ) {
					try {
						resultSet.close();
					}
					catch( SQLException e ) {
					}
					resultSet = null;
				}
			}
	}

	public void deleteTenantByIdIdx( CFSecAuthorization Authorization,
		CFSecTenantPKey argKey )
	{
		deleteTenantByIdIdx( Authorization,
			argKey.getRequiredId() );
	}

	public void deleteTenantByClusterIdx( CFSecAuthorization Authorization,
		long argClusterId )
	{
		final String S_ProcName = "deleteTenantByClusterIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tenant_by_clusteridx( ?, ?, ?, ?, ?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByClusterIdx == null ) {
					stmtDeleteByClusterIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByClusterIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByClusterIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByClusterIdx.setLong( argIdx++, argClusterId );
				resultSet = stmtDeleteByClusterIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteTenantByClusterIdx( CFSecAuthorization Authorization,
		CFSecTenantByClusterIdxKey argKey )
	{
		deleteTenantByClusterIdx( Authorization,
			argKey.getRequiredClusterId() );
	}

	public void deleteTenantByUNameIdx( CFSecAuthorization Authorization,
		long argClusterId,
		String argTenantName )
	{
		final String S_ProcName = "deleteTenantByUNameIdx";
		ResultSet resultSet = null;
		try {
				Connection cnx = schema.getCnx();
				String sql = "SELECT " + schema.getLowerDbSchemaName() + ".sp_delete_tenant_by_unameidx( ?, ?, ?, ?, ?" + ", "
					+		"?" + ", "
					+		"?" + " ) as DeletedFlag";
				if( stmtDeleteByUNameIdx == null ) {
					stmtDeleteByUNameIdx = cnx.prepareStatement( sql );
				}
				int argIdx = 1;
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecUserId().toString() );
				stmtDeleteByUNameIdx.setString( argIdx++, ( Authorization == null ) ? "" : Authorization.getSecSessionId().toString() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecClusterId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, ( Authorization == null ) ? 0 : Authorization.getSecTenantId() );
				stmtDeleteByUNameIdx.setLong( argIdx++, argClusterId );
				stmtDeleteByUNameIdx.setString( argIdx++, argTenantName );
				resultSet = stmtDeleteByUNameIdx.executeQuery();
				if( resultSet.next() ) {
					boolean deleteFlag = resultSet.getBoolean( 1 );
					if( resultSet.next() ) {
						throw new CFLibRuntimeException( getClass(),
							S_ProcName,
							"Did not expect multi-record response" );
					}
				}
				else {
					throw new CFLibRuntimeException( getClass(),
						S_ProcName,
						"Expected 1 record result set to be returned by delete, not 0 rows" );
				}
		}
		catch( SQLException e ) {
			throw new CFLibDbException( getClass(),
				S_ProcName,
				e );
		}
		finally {
			if( resultSet != null ) {
				try {
					resultSet.close();
				}
				catch( SQLException e ) {
				}
				resultSet = null;
			}
		}
	}

	public void deleteTenantByUNameIdx( CFSecAuthorization Authorization,
		CFSecTenantByUNameIdxKey argKey )
	{
		deleteTenantByUNameIdx( Authorization,
			argKey.getRequiredClusterId(),
			argKey.getRequiredTenantName() );
	}

	/**
	 *	Release the prepared statements.
	 *	<p>
	 *	When the schema changes connections, the prepared statements
	 *	have to be released because they contain connection-specific
	 *	information for most databases.
	 */
	public void releasePreparedStatements() {
		final String S_ProcName = "releasePreparedStatements";
		S_sqlSelectTenantDistinctClassCode = null;
		S_sqlSelectTenantBuff = null;

		if( stmtReadBuffByPKey != null ) {
			try {
				stmtReadBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByPKey = null;
		}
		if( stmtReadBuffAll != null ) {
			try {
				stmtReadBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffAll = null;
		}
		if( stmtPageBuffAll != null ) {
			try {
				stmtPageBuffAll.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffAll = null;
		}
		if( stmtLockBuffByPKey != null ) {
			try {
				stmtLockBuffByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtLockBuffByPKey = null;
		}
		if( stmtCreateByPKey != null ) {
			try {
				stmtCreateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtCreateByPKey = null;
		}
		if( stmtUpdateByPKey != null ) {
			try {
				stmtUpdateByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtUpdateByPKey = null;
		}
		if( stmtDeleteByPKey != null ) {
			try {
				stmtDeleteByPKey.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByPKey = null;
		}
		if( stmtPageBuffByClusterIdx != null ) {
			try {
				stmtPageBuffByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtPageBuffByClusterIdx = null;
		}
		if( stmtDeleteByIdIdx != null ) {
			try {
				stmtDeleteByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByIdIdx = null;
		}
		if( stmtDeleteByClusterIdx != null ) {
			try {
				stmtDeleteByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByClusterIdx = null;
		}
		if( stmtDeleteByUNameIdx != null ) {
			try {
				stmtDeleteByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtDeleteByUNameIdx = null;
		}
		if( stmtReadAllBuff != null ) {
			try {
				stmtReadAllBuff.close();
			}
			catch( SQLException e ) {
			}
			stmtReadAllBuff = null;
		}
		if( stmtReadBuffByIdIdx != null ) {
			try {
				stmtReadBuffByIdIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByIdIdx = null;
		}
		if( stmtReadBuffByClusterIdx != null ) {
			try {
				stmtReadBuffByClusterIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByClusterIdx = null;
		}
		if( stmtReadBuffByUNameIdx != null ) {
			try {
				stmtReadBuffByUNameIdx.close();
			}
			catch( SQLException e ) {
			}
			stmtReadBuffByUNameIdx = null;
		}
		if( stmtSelectNextTSecGroupIdGen != null ) {
			try {
				stmtSelectNextTSecGroupIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextTSecGroupIdGen = null;
		}
		if( stmtSelectNextTSecGrpIncIdGen != null ) {
			try {
				stmtSelectNextTSecGrpIncIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextTSecGrpIncIdGen = null;
		}
		if( stmtSelectNextTSecGrpMembIdGen != null ) {
			try {
				stmtSelectNextTSecGrpMembIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextTSecGrpMembIdGen = null;
		}
		if( stmtSelectNextMajorVersionIdGen != null ) {
			try {
				stmtSelectNextMajorVersionIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextMajorVersionIdGen = null;
		}
		if( stmtSelectNextMinorVersionIdGen != null ) {
			try {
				stmtSelectNextMinorVersionIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextMinorVersionIdGen = null;
		}
		if( stmtSelectNextSubProjectIdGen != null ) {
			try {
				stmtSelectNextSubProjectIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextSubProjectIdGen = null;
		}
		if( stmtSelectNextTldIdGen != null ) {
			try {
				stmtSelectNextTldIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextTldIdGen = null;
		}
		if( stmtSelectNextTopDomainIdGen != null ) {
			try {
				stmtSelectNextTopDomainIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextTopDomainIdGen = null;
		}
		if( stmtSelectNextTopProjectIdGen != null ) {
			try {
				stmtSelectNextTopProjectIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextTopProjectIdGen = null;
		}
		if( stmtSelectNextChainIdGen != null ) {
			try {
				stmtSelectNextChainIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextChainIdGen = null;
		}
		if( stmtSelectNextEnumTagIdGen != null ) {
			try {
				stmtSelectNextEnumTagIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextEnumTagIdGen = null;
		}
		if( stmtSelectNextIndexColIdGen != null ) {
			try {
				stmtSelectNextIndexColIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextIndexColIdGen = null;
		}
		if( stmtSelectNextParamIdGen != null ) {
			try {
				stmtSelectNextParamIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextParamIdGen = null;
		}
		if( stmtSelectNextRelationColIdGen != null ) {
			try {
				stmtSelectNextRelationColIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextRelationColIdGen = null;
		}
		if( stmtSelectNextScopeIdGen != null ) {
			try {
				stmtSelectNextScopeIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextScopeIdGen = null;
		}
		if( stmtSelectNextValueIdGen != null ) {
			try {
				stmtSelectNextValueIdGen.close();
			}
			catch( SQLException e ) {
			}
			stmtSelectNextValueIdGen = null;
		}
	}
}
